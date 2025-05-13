from .on_peut_louer import OnPeutLouer

class Assiette(OnPeutLouer):
    def __init__(self, nom):
        super().__init__(nom)
