const express = require("express")
const mysql = require("mysql")
const app = express()
app.use(express.urlencoded({ extended: true }));
const cors = require('cors');
app.use(cors());

const port = 8000

const connectDB = mysql.createConnection({
    host: 'localhost',
    database: 'prueba',
    user: 'root',
    password: ''

})
connectDB.connect(function (error) {
    if (error) {
        throw error;
    } else {
        console.log("TODO BIEN")
    }
});

+
app.post('/', (req, res) => {
    const numero = req.body.number;
    const type = req.body.type;
    console.log(`SELECT ${type} FROM datos WHERE base = ${numero}`)
    connectDB.query(`SELECT ${type} FROM datos WHERE base = ${numero}`, function (error, results, fields) {
        if (error) throw error;
        res.send(results[0])
        console.log(results[0])
    })
});

app.listen(port, '0.0.0.0', () => {
    console.log('Servidor Express.js iniciado en el puerto', port);
});


