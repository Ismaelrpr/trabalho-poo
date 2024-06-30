from random import shuffle

def embaralhar_arquivo(nome_arquivo):
    with open(nome_arquivo, "r") as arquivo:
        linhas = arquivo.readlines()
    shuffle(linhas)

    with open(nome_arquivo, "w") as arquivo:
        arquivo.writelines(linhas)

embaralhar_arquivo("listaclientes.csv")