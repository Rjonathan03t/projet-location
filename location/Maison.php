<?php
require_once 'OnPeutLouer.php';

class Maison extends OnPeutLouer {
    public function __construct(string $nom) {
        parent::__construct($nom);
    }
}
