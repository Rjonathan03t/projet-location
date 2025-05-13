<?php

class OnPeutLouer {
    public string $nom;
    public bool $etat;
    public int $duree;

    public function __construct(string $nom) {
        $this->nom = $nom;
        $this->etat = true;
        $this->duree = 0;
    }
}
