-module(simple).
-export([server/1,client/1,start/1,spawn_n/2,main/1,owner/1]).

server(State)->
	receive 
		{request,Return_PID}->
			io:format("SERVER~w:Clientrequestreceivedfrom~w~n",
			[self(),Return_PID]),
			NewState=State+1,
			Return_PID!{hit_count,NewState},
			server(NewState);
		reset ->
			io:format("Server is resetting hitcount"),
			server(0);
		{server_owner,Owner_PID}->
			Owner_PID!{hit_count,State},
			server(State)
end.

client(Server_Address)->
	Server_Address!{request,self()},
	receive {hit_count,Number}->
		io:format("CLIENT~w:Hitcountwas~w~n",[self(),Number])
end.

start(X)->
	Server_PID=spawn(simple,server,[0]),
	Owner_PID =spawn(simple,owner,[Server_PID]),
	spawn_n(X,Server_PID).

	
spawn_n(N,Server_PID)->
	if N>0->
		spawn(simple,client,[Server_PID]),
		%%Usearandomsleepinmilisecondstosimulatethe
		%%clienttrafficpattern.
		timer:sleep(random:uniform(100)),
		spawn_n(N-1,Server_PID);
	N==0->
	io:format("Lastclientspawned.~n")
end.

main([Arg])->
	N=list_to_integer(atom_to_list(Arg)),
	start(N),
	init:stop().
	
	
owner(Server_PID)->
	timer:sleep(random:uniform(100)),
	Server_PID!{server_owner,self()},
	receive
		{hit_count,Number} when Number <5 ->
			%.....,
			io:format("OWNER ~w :Hitcountwas ~w~n",[self(),Number]),
			owner(Server_PID);
		{hit_count,Number} when Number >4 ->
			%.....,
			io:format("OWNER ~w :Hitcountwas ~w, requesting a reset~n",[self(),Number]),
			Server_PID!reset,
			owner(Server_PID)
	end.