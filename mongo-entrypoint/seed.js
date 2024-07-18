// seed.js
db = db.getSiblingDB('express-cliente');
if (db.clientes.countDocuments({}) === 0) {
  db.clientes.insertMany([
    { nome: 'Cliente1', cpf: '12345678901', celular: '999999999', score: 600, negativado: false },
    { nome: 'Cliente2', cpf: '10987654321', celular: '888888888', score: 400, negativado: true }
  ]);
}

db = db.getSiblingDB('express-service');
if (db.taxas.countDocuments({}) === 0) {
  db.taxas.insertMany([
    { tipo: 'NEGATIVADO', taxas: { '6': 0.04, '12': 0.045, '18': 0.05, '24': 0.053, '36': 0.055 } },
    { tipo: 'ALTA_PONTUACAO', taxas: { '6': 0.02, '12': 0.025, '18': 0.03, '24': 0.035, '36': 0.04 } }
  ]);
}
