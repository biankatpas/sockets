# Sockets
# Projeto: evento cientifico participativo

Projeto para estudo de Sockets UDP e TCP da disciplina de Redes de Computadores I

# Descrição do Trabalho

O sistema a ser desenvolvido é uma aplicação distribuída que visa auxiliar o processo de ensino - aprendizagem durante  
a  apresentação  de  palestras/tutoriais/minicursos  em  eventos  científicos.  Os atores desta  aplicação  são:  os  alunos,  os 
palestrantes  e  o  monitor.  O  monitor  é  um  assistente  do  palestrante  que  pode  responder  algumas  perguntas  mais 
simples no decorrer da apresentação. As perguntas mais complexas são repassadas para que o palestrante as responda no final da apresentação. A aplicação distribuída é composta pelas seguintes aplicações:

# App Aluno: 
através  dessa aplicação, o  usuário  pode  acessar  o AppMonitor para:  (1)  enviar uma pergunta  para  o  palestrante, (2) enviar  mensagem  para  todos  os  participantes  conectados (direcionando primeiro para o AppMonitor). Esta aplicação deve permitir que um 
aluno se identifique para poder ter acesso as funcionalidades oferecidas por este.

# App Monitor: 
deve reencaminhar  as  mensagens (perguntas) recebidas através  da  AppAluno para  o AppPalestrante (quando apropriadas)
ou reencaminhar para todos os participantes (quando apropriadas). Esta aplicação  mantém o registro dos todos  os  usuários que  estão online  no momento e qual o endereço de cada usuário  da  aplicação.  Esta  aplicação    é  usada  pelo  monitor  que  acompanha  o  palestrante  e  por  isso  deve permitir  que  este  selecione  quais  perguntas  devem  ser  encaminhadas  para  o  palestrante  (permite  fazer  o forward da mensagem do aluno – sem modificá-la). A aplicação deve permitir ainda que o monitor responda a mensagem  do  aluno (perguntas  mais  simples)   ou  peça  mais  esclarecimentos  sobre  a  pergunta  para  que  esta 
possa ser encaminhada para o palestrante. O monitor pode ainda enviar arquivos para todos os participantes. Deve ser capaz de receber mensagens de mais de um aluno (mais de uma conexão do AppAluno).

# App Palestrante:
deve  permitir  que  um  palestrante receba as  perguntas  e  selecione  algumas  respostas padrão  (1. não  há  mais  tempo  para  perguntas, 2. pergunta  fora  do  contexto da  palestra, 3.  A pergunta  será respondida  para  todos  os  participantes, 
4. pergunta  será  respondida  individualmente posteriormente). A mensagem do palestrante é sempre respondida para o AppMonitor responsável por encaminhar as mensagens para o AppAluno.

# Obs.:
Transferências  de  arquivos  devem  ser  com  sockets  TCP  e  troca  de  mensagens devem  ser  com  sockets UDP. 
Recomenda-se usar multicast UDP quando as mensagens forem para todos os participantes.
