    var exec = require('cordova/exec');

    var MyOwnPlugin = function() {};

  MyOwnPlugin.prototype.managewifi = function(successCallback, errorCallback,listwifi) {
       exec(successCallback, errorCallback, 'gps', 'gpsinfo', []);
     };
    
       MyOwnPlugin.prototype.getdevice = function(successCallback, errorCallback) {
       exec(successCallback, errorCallback, 'gps', 'changegps', []);
     };
//       MyOwnPlugin.prototype.info = function(successCallback, errorCallback) {
//       exec(successCallback, errorCallback, 'wifi', 'wifinfo', []);
//     };
//      MyOwnPlugin.prototype.changewifi = function(successCallback, errorCallback) {
//       exec(successCallback, errorCallback, 'wifi', 'changewifi', []);
//     };
    module.exports = new MyOwnPlugin();
