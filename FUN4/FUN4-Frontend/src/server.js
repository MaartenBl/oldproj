const express = require('express');

const app = express();
const path = require('path');

app.use(express.static('./dist/my-app'));

app.get('/', function(req, res) {
  res.sendFile('/index.html', { root: __dirname });
});

app.listen(process.env.PORT || 46361);