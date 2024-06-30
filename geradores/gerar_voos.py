from random import randint, sample
def gerar_voos():
    siglas = ['BEL', 'FOR', 'REC', 'BSB', 'CGB', 'NAT', 'MAO', 'VCP', 'POA', 'FLN', 'MCZ', 'IGU', 'CNF', 'GRU', 'CWB', 'GIG', 'AJU', 'SDU', 'CGH', 'JPA']

    with open("listavoos.csv", "w") as arquivo:
        for c in range(500):
            sigla1, sigla2 = sample(siglas, 2) #pega 2 siglas aleatórias unicas

            dia = str(randint(1, 30)).zfill(2) #zfill deixa no formato 00, 01, etc
            mes = str(randint(7, 12)).zfill(2)
            hora = str(randint(0, 23)).zfill(2)

            linha = f"{sigla1};{sigla2};{dia}/{mes}/2024;{hora}:00;{randint(1, 5)} assentos;R$ {randint(100, 1000)}\n"
            arquivo.write(linha)
gerar_voos()
