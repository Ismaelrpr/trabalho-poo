from random import randint
def gerar_hoteis():
    siglas = ['BEL', 'FOR', 'REC', 'BSB', 'CGB', 'NAT', 'MAO', 'VCP', 'POA', 'FLN', 'MCZ', 'IGU', 'CNF', 'GRU', 'CWB', 'GIG', 'AJU', 'SDU', 'CGH', 'JPA']

    with open("listahoteis.csv", "w") as arquivo:
        for c in range(len(siglas)):
            for i in range(1, 6):
                linha = f"{siglas[c]};Hotel {i};R$ {randint(100, 1000)};{randint(1, 5)} estrelas\n"
                arquivo.write(linha)
gerar_hoteis()
