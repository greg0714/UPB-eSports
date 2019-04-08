var clientInfo = {
    client_id : '@!32F2.B458.9918.7996!0001!D0B3.8E33!0008!4A76.F928.A912.3956',
    redirect_uri : 'http://localhost:8000/home.html'
};
var providerInfo = OIDC.discover('https://auth.upbesports.com');

OIDC.setClientInfo(clientInfo);
OIDC.setProviderInfo(providerInfo);
OIDC.storeInfo(providerInfo, clientInfo);
sessionStorage.removeItem('state');
sessionStorage.removeItem('nonce');

var login = function() {
    OIDC.login({
        scope : 'openid email uma_protection',
        response_type : 'token',
        max_age : 60
    });
};

var register = function() {
    window.open('https://auth.upbesports.com/identity/register');
}