
function getData(){

};
function httpGetAsync(theUrl, callback) {
    var xmlHttp = new XMLHttpRequest();
    xmlHttp.onreadystatechange = function() {
        if (xmlHttp.readyState == 4 && xmlHttp.status == 200)
            callback(xmlHttp.responseText);
    }
    xmlHttp.open("GET", theUrl, true); // true for asynchronous
    xmlHttp.send(null);
};




function getLikes(videoId){
    var str ='https://www.googleapis.com/youtube/v3/videos?part=statistics&id=';
    //console.log(str);
    str+= String(videoId);
    str+='&key=AIzaSyDXKEiZzKTDzYIaRUG63nK7dO-h5Yykfyk';
    //console.log(str);

    var item =httpGetAsync(String(str),function(data){
        let j = JSON.parse(data);
//	console.log("***** data obj: "+data);
//	console.log("-----------------------------------------------------------------");
//	console.log("***** j obj: "+j);
//	console.log("-----------------------------------------------------------------");
        let stat=j.items[0].statistics;//likeCount
//	console.log("----- stat obj:    "+ stat);
//	console.log("-----------------------------------------------------------------");
        let like=stat.likeCount;
        console.log("likes number: "+like);
    });

};

function getCategoryId(videoId){
    var str ='https://www.googleapis.com/youtube/v3/videos?part=snippet&id=';
    //console.log(str);
    str+= String(videoId);
    str+='&key=AIzaSyDXKEiZzKTDzYIaRUG63nK7dO-h5Yykfyk';
    //console.log(str);

    var item =httpGetAsync(String(str),function(data){
        let j = JSON.parse(data);
        /*	console.log("***** data obj: "+data);
            console.log("-----------------------------------------------------------------");
            console.log("***** j obj: "+j);
            console.log("-----------------------------------------------------------------");
        */
        let stat=j.items[0].snippet;//CategoryId
//	console.log("----- stat obj:    "+ stat);
        let like=stat.categoryId;
//	console.log("-----------------------------------------------------------------");
        console.log("Category Id: "+like);
    });

};

function getTags(videoId){
    var str ='https://www.googleapis.com/youtube/v3/videos?part=snippet&id=';
    //console.log(str);
    str+= String(videoId);
    str+='&key=AIzaSyDXKEiZzKTDzYIaRUG63nK7dO-h5Yykfyk';
    //console.log(str);

    var item =httpGetAsync(String(str),function(data){
        let j = JSON.parse(data);
        /*	console.log("***** data obj: "+data);
            console.log("-----------------------------------------------------------------");
            console.log("***** j obj: "+j);
            console.log("-----------------------------------------------------------------");
        */
        let stat=j.items[0].snippet;//CategoryId
//	console.log("----- stat obj:    "+ stat);
        let tag=stat.tags;
//	console.log("-----------------------------------------------------------------");
        console.log("Tags: "+tag);
    });

};

