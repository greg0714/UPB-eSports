var storage = window.localStorage;
var apiUrl = 'https://localhost:8443';

var statusCodeDefaults = {
    401: function(){window.location = "http://localhost:8000/index.html"},
    404: function(){alert("Resource not found.")},
    409: function(){alert('An entity with that name already exists in the database. Please double-check that you are not creating a duplicate.')},
    500: function(){
        if(confirm("There was an error with your request. This is sometimes due to an expired login. Would you like to try logging in again?")){
            storage.removeItem('access_token');
            window.location = 'http://localhost:8000/index.html';
        }
    }
};

logout = function(){
    storage.removeItem('access_token');
    storage.removeItem('token_expiry');
    window.location = "http://localhost:8000/index.html"
}

loadHomePage = function() {
    $.ajax({
        url: apiUrl + '/',
        headers: {Authorization: 'Bearer ' + storage.getItem('access_token')},
        statusCode: statusCodeDefaults,
        success: function(data){
            $('#welcome-title').html('Welcome, ' + data + '!');
        }
    });
};

loadGamesPage = function(){
    $.ajax({
        url: apiUrl + '/teamTracker/getGames',
        statusCode: statusCodeDefaults,
        success: function(data){
            gameData = data;
            var tableHtml = '';
            data.forEach(function(game) {
                tableHtml += '<tr>';
                tableHtml += '<th>' + game.name + '</th>';
                tableHtml += '<td>' + '$' + parseFloat(game.cost, 10).toFixed(2).replace(/(\d)(?=(\d{3})+\.)/g, "$1,").toString() + '</td>';
                tableHtml += '<td>' + game.platforms + '</td>';
                tableHtml += '<td>' + (game.subscription ? 'Yes' : 'No') + '</td>';
                tableHtml += '<td>' + (game.microtransactions ? 'Yes' : 'No') + '</td>';
                tableHtml += '<td>' + game.updated + '</td>';
                tableHtml += '<td>' + game.updatedBy + '</td>';
                tableHtml += '</tr>';
            });
            $('#games-table').html(tableHtml);
            loadGamesForm(data);
        }
    });
};

loadPlayersPage = function() {
    $.ajax({
        url: apiUrl + '/teamTracker/getPlayers',
        statusCode: statusCodeDefaults,
        success: function(data){
            players = data;
            var tableHtml = '';
            data.forEach(function(player) {
                tableHtml += '<tr>';
                tableHtml += '<th>' + player.displayName + '</th>';
                tableHtml += '<td>' + player.firstName + (player.middleName ? ' ' + player.middleName : '') + ' ' + player.lastName + '</td>';
                tableHtml += '<td>' + (player.eligible ? 'Yes' : 'No') + '</td>';
                tableHtml += '<td>' + player.joined + '</td>';
                tableHtml += '<td>' + player.updated + '</td>';
                tableHtml += '<td>' + player.updatedBy + '</td>';
                tableHtml += '</tr>';
            });
            $('#players-table').html(tableHtml);
            loadPlayersForm(data);
        }
    });
};

loadTeamsPage = function() {
    $.ajax({
        url: apiUrl + '/teamTracker/getTeams',
        statusCode: statusCodeDefaults,
        success: function(data){
            teams = data;
            var tableHtml = '';
            data.forEach(function(team) {
                tableHtml += '<tr>';
                tableHtml += '<th>' + team.name + '</th>';
                tableHtml += '<td>' + team.game + '</td>';
                tableHtml += '<td>' + team.managerName + '</td>';
                tableHtml += '<td>' + team.wins + '/' + team.losses + '/' + team.draws + '</td>';
                tableHtml += '<td>';
                team.players.forEach(function(player){tableHtml += player.displayName + '<br/>';});
                tableHtml += '</td>';
                tableHtml += '<td>' + team.updated + '</td>';
                tableHtml += '<td>' + team.updatedBy + '</td>';
                tableHtml += '</tr>';
            });
            $('#teams-table').html(tableHtml);
            loadTeamsForm(data);
        }
    });
};

function loadSchedulingPage(){
    $.ajax({
        url: apiUrl + '/scheduling/getSchedules',
        statusCode: statusCodeDefaults,
        success: function(data){
            var schedulesHtml = '';
            data.forEach(function(schedule) {
                schedulesHtml += '<li data-role="collapsible" data-iconpos="right" data-inset="false">';
                schedulesHtml += '<h2>' + schedule.teamName + ' - ' + schedule.eventType + ' on ' + schedule.eventDate + '</h2>';
                schedulesHtml += '<ul data-role="listview" data-theme="b">';
                schedulesHtml += '<li><strong>Team: </strong>' + schedule.teamName + '</li>';
                schedulesHtml += '<li><strong>Type: </strong>' + schedule.eventType + '</li>';
                schedulesHtml += '<li><strong>Time: </strong>' + schedule.eventDate + '</li>';
                schedulesHtml += '<li><strong>Location: </strong>' + schedule.location + '</li>';
                schedulesHtml += '<li><strong>Comments: </strong>' + schedule.comments + '</li>';
                schedulesHtml += '<li><strong>Updated: </strong>' + schedule.updated + '</li>';
                schedulesHtml += '<li><strong>Updated By: </strong>' + schedule.updatedBy + '</li>';
                schedulesHtml += '</ul>';
                schedulesHtml += '</li>';
            });
            $('#schedules-view').html(schedulesHtml);
            loadSchedulingForm(data);
        }
    });
}

loadGamesForm = function(games){
    var selectOptions = '';
    games.forEach(function(game){
        selectOptions += '<option value="' + game.id + '">' + game.name + '</option>'
    });
    $('#game-select').html(selectOptions);
    $('#team-game-select').html(selectOptions);
    $('#game-add-modify-remove:not(.bound)').addClass('bound').on('change', function(event){
        var submitType = $('#game-add-modify-remove').val();
        if(submitType == 'add') {
            $('#game-select').selectmenu('disable');
            $('#game-name').prop('readonly', false);
            $('#game-cost').prop('readonly', false);
            $('#game-subscription-yes').prop('disabled', false);
            $('#game-subscription-no').prop('disabled', false);
            $('#game-microtransactions-yes').prop('disabled', false);
            $('#game-microtransactions-no').prop('disabled', false);
            $('#game-platforms').prop('readonly', false);
            clearGameForm(event);
        }
        else if(submitType == 'modify'){
            $('#game-select').selectmenu('enable');
            $('#game-name').prop('readonly', true);
            $('#game-cost').prop('readonly', false);
            $('#game-subscription-yes').prop('disabled', false);
            $('#game-subscription-no').prop('disabled', false);
            $('#game-microtransactions-yes').prop('disabled', false);
            $('#game-microtransactions-no').prop('disabled', false);
            $('#game-platforms').prop('readonly', false);
            loadGameIntoForm(event, games);
        } 
        else if(submitType == 'remove'){
            $('#game-select').selectmenu('enable');
            $('#game-name').prop('readonly', true);
            $('#game-cost').prop('readonly', true);
            $('#game-subscription-yes').prop('disabled', true);
            $('#game-subscription-no').prop('disabled', true);
            $('#game-microtransactions-yes').prop('disabled', true);
            $('#game-microtransactions-no').prop('disabled', true);
            $('#game-platforms').prop('readonly', true);
            loadGameIntoForm(event, games);
        }
    });

    $('#game-select:not(.bound)').addClass('bound').on('change', function(event) {loadGameIntoForm(event, games);});
    $('#game-form:not(.bound)').addClass('bound').submit(gameFormSubmit);
};

function loadGameIntoForm(event, games){
    games.forEach(function(game){
        if(game.id == $('#game-select').val()){
            $('#game-name').val(game.name);
            $('#game-cost').val(game.cost);
            $(game.subscription ? '#game-subscription-yes' : '#game-subscription-no').prop('checked', true).checkboxradio('refresh');
            $(!game.subscription ? '#game-subscription-yes' : '#game-subscription-no').prop('checked', false).checkboxradio('refresh');
            $(game.microtransactions ? '#game-microtransactions-yes' : '#game-microtransactions-no').prop('checked', true).checkboxradio('refresh');
            $(!game.microtransactions ? '#game-microtransactions-yes' : '#game-microtransactions-no').prop('checked', false).checkboxradio('refresh');
            $('#game-platforms').val(game.platforms);
        }
    });
}

function clearGameForm(event){
    $('#game-form').trigger("reset");
}

function gameFormSubmit(event){
    event.preventDefault();
    if(new Date() > new Date(storage.getItem('token_expiry'))){
        alert('Your login has expired. Please log in again.');
        window.location = 'http://localhost:8000/index.html';
    }
    else{
        var submitType = $('#game-add-modify-remove').val();
        var url = apiUrl + '/teamTracker/';
        if(submitType == 'add' || submitType == 'modify') url += 'addOrUpdateGame';
        else url += 'removeGame';
        $.ajax({
            type: "POST",
            url: url,
            headers: {Authorization: 'Bearer ' + storage.getItem('access_token')},
            data: $('#game-form').serialize(),
            statusCode: statusCodeDefaults,
            success: function(){
                if(submitType != 'modify') $('#game-form').trigger("reset");
                alert('You were able to successfully ' + submitType + ' the game.');
                loadGamesPage();
            }
        });
    }
}

loadTeamsForm = function(teams){
    var selectOptions = '';
    teams.forEach(function(team){
        selectOptions += '<option value="' + team.id + '">' + team.name + '</option>'
    });
    $('#team-select').html(selectOptions);
    $('#team-player-team-select').html(selectOptions);
    $('#schedule-team-select').html(selectOptions);
    $('#team-add-modify-remove:not(.bound)').addClass('bound').on('change', function(event){
        var submitType = $('#team-add-modify-remove').val();
        if(submitType == 'add') {
            $('#team-select').selectmenu('disable');
            $('#team-game-select').selectmenu('enable');
            $('#team-manager-select').selectmenu('enable');
            $('#team-name').prop('readonly', false);
            $('#team-wins').prop('readonly', false);
            $('#team-losses').prop('readonly', false);
            $('#team-draws').prop('readonly', false);
            clearTeamForm(event);
        }
        else if(submitType == 'modify'){
            $('#team-select').selectmenu('enable');
            $('#team-game-select').selectmenu('disable');
            $('#team-manager-select').selectmenu('enable');
            $('#team-name').prop('readonly', false);
            $('#team-wins').prop('readonly', false);
            $('#team-losses').prop('readonly', false);
            $('#team-draws').prop('readonly', false);
            loadTeamIntoForm(event, teams);
        } 
        else if(submitType == 'remove'){
            $('#team-select').selectmenu('enable');
            $('#team-game-select').selectmenu('disable');
            $('#team-manager-select').selectmenu('disable');
            $('#team-name').prop('readonly', true);
            $('#team-wins').prop('readonly', true);
            $('#team-losses').prop('readonly', true);
            $('#team-draws').prop('readonly', true);
            loadTeamIntoForm(event, teams);
        }
    });

    $('#team-select:not(.bound)').addClass('bound').on('change', function(event) {loadTeamIntoForm(event, teams);});
    $('#team-form:not(.bound)').addClass('bound').submit(teamFormSubmit);
};

function loadTeamIntoForm(event, teams){
    teams.forEach(function(team){
        if(team.id == $('#team-select').val()){
            $('#team-game-select').val(team.game).selectmenu('refresh');
            $('#team-manager-select').val(team.manager).selectmenu('refresh');
            $('#team-name').val(team.name);
            $('#team-wins').val(team.wins);
            $('#team-losses').val(team.losses);
            $('#team-draws').val(team.draws);
        }
    });
}

function clearTeamForm(event){
    $('#team-form').trigger("reset");
}

function teamFormSubmit(event){
    event.preventDefault();
    if(new Date() > new Date(storage.getItem('token_expiry'))){
        alert('Your login has expired. Please log in again.');
        window.location = 'http://localhost:8000/index.html';
    }
    else{
        var submitType = $('#team-add-modify-remove').val();
        var url = apiUrl + '/teamTracker/';
        if(submitType == 'add' || submitType == 'modify') url += 'addOrUpdateTeam';
        else url += 'removeTeam';
        $.ajax({
            type: "POST",
            url: url,
            headers: {Authorization: 'Bearer ' + storage.getItem('access_token')},
            data: $('#team-form').serialize(),
            statusCode: statusCodeDefaults,
            success: function(){
                if(submitType != 'modify') $('#team-form').trigger("reset");
                alert('You were able to successfully ' + submitType + ' the team.');
                loadTeamsPage();
            }
        });
    }
}

loadPlayersForm = function(players){
    var selectOptions = '';
    players.forEach(function(player){
        selectOptions += '<option value="' + player.id + '">' + player.displayName + '</option>'
    });
    $('#player-select').html(selectOptions);
    $('#team-manager-select').html(selectOptions);
    $('#team-player-player-select').html(selectOptions);
    $('#player-add-modify-remove:not(.bound)').addClass('bound').on('change', function(event){
        var submitType = $('#player-add-modify-remove').val();
        if(submitType == 'add') {
            $('#player-select').selectmenu('disable');
            $('#player-first-name').prop('readonly', false);
            $('#player-middle-name').prop('readonly', false);
            $('#player-last-name').prop('readonly', false);
            $('#player-display-name').prop('readonly', false);
            $('#player-eligible-yes').prop('disabled', false);
            $('#player-eligible-no').prop('disabled', false);
            clearPlayerForm(event);
        }
        else if(submitType == 'modify'){
            $('#player-select').selectmenu('enable');
            $('#player-first-name').prop('readonly', true);
            $('#player-middle-name').prop('readonly', true);
            $('#player-last-name').prop('readonly', true);
            $('#player-display-name').prop('readonly', false);
            $('#player-eligible-yes').prop('disabled', false);
            $('#player-eligible-no').prop('disabled', false);
            loadPlayerIntoForm(event, players);
        } 
        else if(submitType == 'remove'){
            $('#player-select').selectmenu('enable');
            $('#player-first-name').prop('readonly', true);
            $('#player-middle-name').prop('readonly', true);
            $('#player-last-name').prop('readonly', true);
            $('#player-display-name').prop('readonly', true);
            $('#player-eligible-yes').prop('disabled', true);
            $('#player-eligible-no').prop('disabled', true);
            loadPlayerIntoForm(event, players);
        }
    });

    $('#player-select:not(.bound)').addClass('bound').on('change', function(event) {loadPlayerIntoForm(event, players);});
    $('#player-form:not(.bound)').addClass('bound').submit(playerFormSubmit);
};

function loadPlayerIntoForm(event, players){
    players.forEach(function(player){
        if(player.id == $('#player-select').val()){
            $('#player-first-name').val(player.firstName);
            $('#player-middle-name').val(player.middleName);
            $('#player-last-name').val(player.lastName);
            $('#player-display-name').val(player.displayName);
            $(player.eligible ? '#player-eligible-yes' : '#player-eligible-no').prop('checked', true).checkboxradio('refresh');
            $(!player.eligible ? '#player-eligible-yes' : '#player-eligible-no').prop('checked', false).checkboxradio('refresh');
        }
    });
}

function clearPlayerForm(event){
    $('#player-form').trigger("reset");
}

function playerFormSubmit(event){
    event.preventDefault();
    if(new Date() > new Date(storage.getItem('token_expiry'))){
        alert('Your login has expired. Please log in again.');
        window.location = 'http://localhost:8000/index.html';
    }
    else{
        var submitType = $('#player-add-modify-remove').val();
        var url = apiUrl + '/teamTracker/';
        if(submitType == 'add' || submitType == 'modify') url += 'addOrUpdatePlayer';
        else url += 'removePlayer';
        $.ajax({
            type: "POST",
            url: url,
            headers: {Authorization: 'Bearer ' + storage.getItem('access_token')},
            data: $('#player-form').serialize(),
            statusCode: statusCodeDefaults,
            success: function(){
                if(submitType != 'modify') $('#player-form').trigger("reset");
                alert('You were able to successfully ' + submitType + ' the player.');
                loadPlayersPage();
            }
        });
    }
}

function loadTeamPlayersForm(){
    $('#team-player-form:not(.bound)').addClass('bound').submit(teamPlayerFormSubmit);
}

function teamPlayerFormSubmit(event){
    event.preventDefault();
    if(new Date() > new Date(storage.getItem('token_expiry'))){
        alert('Your login has expired. Please log in again.');
        window.location = 'http://localhost:8000/index.html';
    }
    else{
        var submitType = $('#team-player-add-remove').val();
        var url = apiUrl + '/teamTracker/';
        if(submitType == 'add') url += 'addPlayerToTeam';
        else url += 'removePlayerFromTeam';
        $.ajax({
            type: "POST",
            url: url,
            headers: {Authorization: 'Bearer ' + storage.getItem('access_token')},
            data: $('#team-player-form').serialize(),
            statusCode: statusCodeDefaults,
            success: function(data){
                if(data) alert('You were able to successfully ' + submitType + ' the player.');
                else alert('The selected player is ' + (submitType == 'add' ? 'already' : 'not') + ' on the selected team.');
                loadPlayersPage();
                loadTeamsPage();
            }
        });
    }
}

loadSchedulingForm = function(schedules){
    var selectOptions = '';
    schedules.forEach(function(schedule){
        selectOptions += '<option value="' + schedule.id + '">' + schedule.teamName + ' - ' + schedule.eventType + ' on ' + schedule.eventDate + '</option>'
    });
    $('#schedule-select').html(selectOptions);
    $('#schedule-add-modify-remove:not(.bound)').addClass('bound').on('change', function(event){
        var submitType = $('#schedule-add-modify-remove').val();
        if(submitType == 'add') {
            $('#schedule-select').selectmenu('disable');
            $('#schedule-team-select').selectmenu('enable');
            $('#schedule-type-select').selectmenu('enable');
            $('#schedule-location').prop('readonly', false);
            $('#schedule-date').prop('readonly', false);
            $('#schedule-comments').prop('readonly', false);
            clearScheduleForm(event);
        }
        else if(submitType == 'modify'){
            $('#schedule-select').selectmenu('enable');
            $('#schedule-team-select').selectmenu('enable');
            $('#schedule-type-select').selectmenu('enable');
            $('#schedule-location').prop('readonly', false);
            $('#schedule-date').prop('readonly', false);
            $('#schedule-comments').prop('readonly', false);
            loadScheduleIntoForm(event, schedules);
        } 
        else if(submitType == 'remove'){
            $('#schedule-select').selectmenu('enable');
            $('#schedule-team-select').selectmenu('disable');
            $('#schedule-type-select').selectmenu('disable');
            $('#schedule-location').prop('readonly', true);
            $('#schedule-date').prop('readonly', true);
            $('#schedule-comments').prop('readonly', true);
            loadScheduleIntoForm(event, schedules);
        }
    });

    $('#schedule-select:not(.bound)').addClass('bound').on('change', function(event) {loadScheduleIntoForm(event, schedules);});
    $('#schedule-form:not(.bound)').addClass('bound').submit(scheduleFormSubmit);
};

function loadScheduleIntoForm(event, schedules){
    schedules.forEach(function(schedule){
        if(schedule.id == $('#schedule-select').val()){
            $('#schedule-team-select').val(schedule.teamId).selectmenu('refresh');
            $('#schedule-type-select').val(schedule.eventType).selectmenu('refresh');
            $('#schedule-location').val(schedule.location);
            $('#schedule-date').val(schedule.eventTime);
            $('#schedule-comments').val(schedule.comments);
        }
    });
}

function clearScheduleForm(event){
    $('#schedule-form').trigger("reset");
}

function scheduleFormSubmit(event){
    event.preventDefault();
    if(new Date() > new Date(storage.getItem('token_expiry'))){
        alert('Your login has expired. Please log in again.');
        window.location = 'http://localhost:8000/index.html';
    }
    else{
        var submitType = $('#schedule-add-modify-remove').val();
        var url = apiUrl + '/scheduling/';
        if(submitType == 'add' || submitType == 'modify') url += 'addOrUpdateSchedule';
        else url += 'removeSchedule';
        $.ajax({
            type: "POST",
            url: url,
            headers: {Authorization: 'Bearer ' + storage.getItem('access_token')},
            data: $('#schedule-form').serialize(),
            statusCode: statusCodeDefaults,
            success: function(){
                if(submitType != 'modify') $('#schedule-form').trigger("reset");
                alert('You were able to successfully ' + submitType + ' the event.');
                loadSchedulingPage();
            }
        });
    }
}

$(document).ready(function() {
    if(window.location.href.includes('access_token')){
        OIDC.restoreInfo();
        var accessToken = OIDC.getAccessToken();
        var expiry = new Date();
        expiry.setHours(expiry.getHours() + 1);
        storage.setItem("access_token", accessToken);
        storage.setItem("token_expiry", expiry);
        window.location = '/home.html'
    }

    loadHomePage();
    loadGamesPage();
    loadPlayersPage();
    loadTeamsPage();
    loadTeamPlayersForm();
    loadSchedulingPage();
});