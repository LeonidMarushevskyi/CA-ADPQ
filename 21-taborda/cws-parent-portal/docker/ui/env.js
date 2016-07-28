// Environment settings for CWS Parent Portal UI
//
// Please set baseurl to the location where the UI code, running in a browser, can contact the CWS Parent Portal API
// The default setting works for Docker Compose running on a Mac with Docker Machine using the VirtualBox driver and
// the default host-only network.
//
(function (window) {
    window.__env = window.__env || {};
    window.__env.baseurl = "http://192.168.99.100:8081/";
}(window));