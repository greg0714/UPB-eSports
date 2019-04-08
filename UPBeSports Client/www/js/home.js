var storage = window.localStorage;
var games, players, teams;

var statusCodeDefaults = {
    401: function(){window.location = "http://localhost:8000/index.html"},
    404: function(){alert("Resource not found.")},
    500: function(){
        if(confirm("There was an error with your request. This is sometimes due to an expired login. Would you like to try logging in again?"))
            window.location = 'http://localhost:8000/index.html';
    }
};

loadHomePage = function() {
    $.ajax({
        url: 'https://localhost:8443/',
        headers: {Authorization: 'Bearer ' + storage.getItem('access_token')},
        statusCode: statusCodeDefaults,
        success: function(data){
            $('#welcome-title').html('Welcome, ' + data + '!');
        }
    });
};

loadGamesPage = function(){
    $.ajax({
        url: 'https://localhost:8443/teamTracker/getGames',
        statusCode: statusCodeDefaults,
        success: function(data){
            games = data;
            var tableHtml = '';
            data.forEach(function(game) {
                tableHtml += '<tr>';
                tableHtml += '<th>' + game.name + '</th>';
                tableHtml += '<td>' + game.cost + '</td>';
                tableHtml += '<td>' + game.platforms + '</td>';
                tableHtml += '<td>' + game.subscription + '</td>';
                tableHtml += '<td>' + game.microtransactions + '</td>';
                tableHtml += '<td>' + game.updated + '</td>';
                tableHtml += '<td>' + game.updatedBy + '</td>';
                tableHtml += '</tr>';
            });
            $('#games-table').html(tableHtml);
        }
    });
};

loadPlayersPage = function() {
    $.ajax({
        url: 'https://localhost:8443/teamTracker/getPlayers',
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
        }
    });
};

loadTeamsPage = function() {
    $.ajax({
        url: 'https://localhost:8443/teamTracker/getTeams',
        statusCode: statusCodeDefaults,
        success: function(data){
            teams = data;
            var tableHtml = '';
            data.forEach(function(team) {
                tableHtml += '<tr>';
                tableHtml += '<th>' + team.name + '</th>';
                tableHtml += '<td>' + team.game + '</td>';
                tableHtml += '<td>' + team.manager + '</td>';
                tableHtml += '<td>' + team.wins + '/' + team.losses + '/' + team.draws + '</td>';
                tableHtml += '<td>';
                team.players.forEach(function(player){tableHtml += player.displayName + '<br/>';});
                tableHtml += '</td>';
                tableHtml += '<td>' + team.updated + '</td>';
                tableHtml += '<td>' + team.updatedBy + '</td>';
                tableHtml += '</tr>';
            });
            $('#teams-table').html(tableHtml);
        }
    });
};

$(document).ready(function() {
    OIDC.restoreInfo();
    var access_token = OIDC.getAccessToken();
    storage.setItem("access_token", access_token);

    loadHomePage();
    loadGamesPage();
    loadPlayersPage();
    loadTeamsPage();
});