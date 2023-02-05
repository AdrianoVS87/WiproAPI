# language: pt

  Funcionalidade: Pesquisa de frete por cep consultando API Viacep

    Cenário: Pesquisa de um frete dado um cep válido
      Dado Que a minha aplicação esta inicializada
      Quando Eu pesquiso o cep "20251061"
      Então Eu encontro o frete

    Cenário: Pesquisa de um frete dado um cep inexistente
      Dado Que a minha aplicação esta inicializada
      Quando Eu pesquiso o cep "90480-666"
      Então Eu recebo mensagem de erro CEP não encontrado

    Cenário: Pesquisa de um frete com dados inválidos
      Dado Que a minha aplicação esta inicializada
      Quando Eu pesquiso o cep "90480ABC"
      Então Eu recebo mensagem de erro CEP inválido

