const OnPeutLouer = require('./OnPeutLouer');

class Voiture extends OnPeutLouer {
    constructor(nom) {
        super(nom);
    }
}

module.exports = Voiture;
