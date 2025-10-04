apimock=(function(){

	var mockdata=[];

	mockdata["johnconnor"]=	[
        {author:"johnconnor","points":[{"x":150,"y":120},{"x":215,"y":115}],"name":"house"},
	    {author:"johnconnor","points":[{"x":340,"y":240},{"x":15,"y":215}],"name":"gear"}
    ];
	mockdata["maryweyland"]=[
        {author:"maryweyland","points":[{"x":140,"y":140},{"x":115,"y":115}],"name":"house2"},
	    {author:"maryweyland","points":[{"x":140,"y":140},{"x":115,"y":115}],"name":"gear2"}
    ];
    mockdata["Ana"] = [
        {author: "Ana", name: "car", points: [{x:15,y:15},{x:25,y:25},{x:35,y:35}]},
        {author: "Ana", name: "plane", points: [{x:5,y:5},{x:10,y:10},{x:15,y:15},{x:20,y:20}]}
    ];
    mockdata["Jeimy"] = [
        {author: "Jeimy", name: "house", points: [{x:10,y:10},{x:20,y:20}]},
        {author: "Jeimy", name: "building", points: [{x:10,y:10},{x:30,y:30},{x:40,y:40}]}
    ];


	return {
		getBlueprintsByAuthor:function(authname,callback){
			callback(
				mockdata[authname]
			);
		},

		getBlueprintsByNameAndAuthor:function(authname,bpname,callback){

			callback(
				mockdata[authname].find(function(e){return e.name===bpname})
			);
		}
	}	

})();

/*
Example of use:
var fun=function(list){
	console.info(list);
}

apimock.getBlueprintsByAuthor("johnconnor",fun);
apimock.getBlueprintsByNameAndAuthor("johnconnor","house",fun);*/