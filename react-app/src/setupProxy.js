const { createProxyMiddleware } = require('http-proxy-middleware');

module.exports = function(app) {
    app.use(createProxyMiddleware("/stomp/**", { target: "ws://localhost:8080", ws: true, changeOrigin: true }));
};
