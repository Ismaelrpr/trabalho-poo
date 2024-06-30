from random import randint, sample

def gerar_clientes():
    siglas = ['BEL', 'FOR', 'REC', 'BSB', 'CGB', 'NAT', 'MAO', 'VCP', 'POA', 'FLN', 'MCZ', 'IGU', 'CNF', 'GRU', 'CWB', 'GIG', 'AJU', 'SDU', 'CGH', 'JPA']

    ultimo_local = {}
    dinheiro = {}
    estrelas = {}

    with open("listaclientes.csv", "w") as arquivo:
        for c in range(1000):
            viagens_por_cliente = randint(1, 4)
            for i in range(viagens_por_cliente):
                if c not in ultimo_local:
                    # na primeira viagem, escolher duas siglas aleatórias e inicializar dinheiro e estrelas
                    sigla1, sigla2 = sample(siglas, 2)
                    dinheiro[c] = randint(1000, 10000)
                    estrelas[c] = randint(1, 5)
                else:
                    # nas pr´oximas viagens, usar o último local como origem e manter o mesmo dinheiro e estrelas
                    sigla1 = ultimo_local[c]
                    sigla2 = sample([s for s in siglas if s != sigla1], 1)[0]

                ultimo_local[c] = sigla2

                linha = f"cliente {c};{sigla1};{sigla2};{randint(1, 5)} dias;{estrelas[c]} estrelas;R$ {dinheiro[c]}\n"
                arquivo.write(linha)

gerar_clientes()