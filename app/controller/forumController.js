const express = require('express');
const router = express.Router();

const ForumService = require('../services/forumService');
const admin = require('firebase-admin');
const db = require('../database/firebase.js')(admin);

router.post('/forums', async (req,res) => {
    try {
        let forum = await ForumService.postForums(req.body);
        return res.status(201).send({status: 201, data: forum});
    } catch (e) {
        return res.status(401).send({mensagem: e.message});
    }
});

router.get('/forums', async (req,res) => {
    let forums = await ForumService.getForums();

    console.log(forums)

    return res.status(200).send(forums);
})

module.exports = app => app.use('/api', router);