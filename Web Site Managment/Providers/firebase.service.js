angular.module('orledor').service('firebase' , function(){
	var db = new Firebase("https://fiery-fire-8931.firebaseio.com/");

	return db;
});