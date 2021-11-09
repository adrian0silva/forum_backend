const express = require('express');
const app = express();
const cors = require('cors');
app.use(cors());
const server = require('http').Server(app);

const PORTA = 3000;

server.listen(PORTA,() => {
    console.log("Servidor rodando na porta " + PORTA)
})
app.use(express.urlencoded({ extended: true }));
app.use(express.json());
//var serviceAccount = require("./gerenciamento-das-raspberrys-firebase-adminsdk-nideg-8301982d87.json");

var admin = require("firebase-admin");

var serviceAccount = require("./forum-8541a-firebase-adminsdk-ywsbm-534ff34dc6.json");

admin.initializeApp({
  credential: admin.credential.cert(serviceAccount)
});


require('./app/controller/forumController')(app);