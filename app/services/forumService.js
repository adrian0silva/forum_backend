const uuid = require('uuid');
const admin = require('firebase-admin');
const db = require('../database/firebase.js')(admin);

exports.postForums = async function(data) {
    let id = uuid.v1();
    const docRef = db.collection('forums').doc(id);
    await docRef.set({
        titulo: data.titulo,
        descricao: data.descricao
    });
}

exports.getForums = async function(data) {
    const snapshot = await db.collection('forums').get();
    let dados = [];
    snapshot.forEach((doc) => {
        console.log(doc)
        dados.push({
            "id":doc.id,
            "titulo": doc.data().titulo,
            "descricao": doc.data().descricao
        });
    });

    return dados;
}