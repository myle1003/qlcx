var type = 0;
 var countMess=0;
function change(f)
{
	if(isFileImage(f.files[0])!=-1)
	{
		type=1;
	}else{
		type=2;
	}
	console.log(type);
	$("#sendMess").val(f.files[0].name);
}

function isFileImage(file) {
    const acceptedImageTypes = ['image/gif', 'image/jpeg', 'image/png'];
    return file && $.inArray(file['type'], acceptedImageTypes)
}

var roomId;
var userId;

function createUserAccount (email, displayUser) {
     db.collection("users")
     .add({
          "id" : email,
          "userName" : displayUser
     }).then((err) => {console.log(err)});
}

function createRoomsForUser (email ,displayUser) {

     db.collection("rooms")
     .add({
		  "isClosed" : false,
		  "isCheckAdmin":false,
  		  "timestamp" : moment(new Date()).format("X"),
		  user:{
          "id" : email,
          "displayName" : displayUser,
     }
     }).then( function onSuccess() {
		sessionStorage.setItem("userId",email);
  		window.location.assign("/ui/home");
	} ).catch( function onFailure( err ) {
  		console.log( 'onFailure', err );
	 } );
}


function createAccount ( ) {
     var displayUser = document.getElementById("userNameU").value;
     var email = document.getElementById("nameLoginU").value;
     createUserAccount(email, displayUser);
     createRoomsForUser(email, displayUser);
     sessionStorage.setItem("userId",email);
     // sessionStorage.setItem("userName",displayUsers);
     //location.replace("file:///C:/Users/Phuc/Desktop/KCKH/GIS.html");
}

function findUserId () {
     var nameLogin = document.getElementById("nameLogin").value;
     var check = false;
     db.collection("users")
     .get()
     .then((snap) => {
          snap.forEach((doc) => {
               if(doc.data().id == nameLogin  ) {
                    userId = doc.data().id;
                    check = true;
                    console.log(userId);
                    sessionStorage.setItem("userId",doc.data().id);
                    sessionStorage.setItem("userName",doc.data().userName);
                    findRoomId(doc.data().id);
				
					window.location.assign("/ui/home");
               }
          });
          if (!check) {
               alert("Tài khoản không tồn tại...");
          }
     });
     
}

function autoLoadMess() {
     
     var userIdLogin = sessionStorage.getItem("userId");
     db.collection("users")
     .get()
     .then((snap) => {
          snap.forEach((doc) => {
          console.log(doc.data().id,doc.data().id == userIdLogin)
               if(doc.data().id == userIdLogin  ) {
                    userId = doc.data().id;
                    sessionStorage.setItem("userName",doc.data().userName);
                    findRoomId(doc.data().id);
               }
          });
     });
		
}

if (( sessionStorage.getItem("userId") != null )) {
     document.getElementById("chatBoxFooter").setAttribute("class","d-block");
     document.getElementById("neverLogin").setAttribute("class","d-none");
     autoLoadMess();
} else {
     document.getElementById("chatBoxFooter").setAttribute("class","d-none");
     document.getElementById("neverLogin").setAttribute("class","d-block");
}

function findRoomId (userId) {
     db.collection("rooms").get().then((snap) => {
          snap.forEach((doc) => {
          // doc.data() is never undefined for query doc snapshots
               if(doc.data().user.id == userId) {
                    roomId = doc.id;
                    loadMessages(roomId, userId);
               }
          });
     });
}

function loadMessages (roomId, userId) {
     db.collection("messages")
     .where("groupId" , "==", roomId)
     .orderBy("timestamp")
     .onSnapshot((snap) => {
		 countMess=snap.docs.length;
          document.getElementById("contentMess").innerHTML = " ";
          snap.forEach((doc) => {
				
               var html = "";
               if (doc.data().senderId == userId) {
                    html += "<li class='self' id='message-" + doc.id + "'>";
                    // html += "<button data-id='" 
                    //      + doc.id + "' onclick='deleteMessage(this);'>";
                    // html += "Delete";
                    // html += "</button>";
                    //html += "<span>"
					if(doc.data().type==0)
					{
						html += doc.data().content 
					}else if(doc.data().type==1){
						html += "<img style='padding-right:6px;width:170px;height:130px' src='http://localhost:1005"+doc.data().content+"'/>"
					}else{
						
						html += "<img  style='padding-right:6px;width:170px;height:100px' src='http://localhost:2000/ui/map_location.jpeg' /><p style='margin-bottom:-3px;color:#ccc'>Đã chia sẽ vị trí</p>"
					}
                      
                    //          "</span>";
               } else  {
                    html += "<li class='other' id='message-" + doc.id + "'>";
                    //html += "<span>" + "admin" + " : " 
                    if(doc.data().type==0)
					{
						html += doc.data().content 
					}else if(doc.data().type==1){
						html += "<img style='width:160px;height:120px' src='http://localhost:1005"+doc.data().content+"'/>"
					}else{
						
						html += "<img  style='width:160px;height:100px' src='http://localhost:2000/ui/map_location.jpeg' /><p style='margin-bottom:-3px;color:#ccc'>Đã chia sẽ vị trí</p>"
					}
                    //"</span>";
               }
               html += "</li>";
               document.getElementById("contentMess").innerHTML += html;

          });
     });
	$("#contentMess").animate({scrollTop: 20000000}, "slow");
}  

function sendMessage() {
     var message = document.getElementById('sendMess').value;
	if(message=="")
	{
		return;
	}
	if(type!=0)
	{
    	let type1 = type;
    	type=0;
    	var formData = new FormData();
    	formData.append('file', $('#file')[0].files[0]);
		$.ajax({
            url:"/ui/home/uploadFile",
            method: 'POST',
            enctype: 'multipart/form-data',
            processData: false,
            contentType: false,
            data:formData,
            success: function (data) {
			     db.collection("messages").add({
			          "content" : data.data,
			          "senderId" : userId,
			          "groupId" : roomId,
			          "isRead" : false,
			          "timestamp" : moment(new Date()).format("X"),
			          "type" : type1			
     			});
			},
      	});
	}else{
		 db.collection("messages").add({
			          "content" : message,
			          "senderId" : userId,
			          "groupId" : roomId,
			          "isRead" : false,
			          "timestamp" : moment(new Date()).format("X"),
			          "type" : 0			
     			});
	}
	$("#contentMess").animate({scrollTop: 20000000}, "slow");	
	let roomRef = firebase.firestore().collection("rooms").doc(roomId);
                    roomRef.get().then(doc => {
                        if (!doc.exists)
                            return;
                        roomRef
                                .update({
                                	timestamp: moment(new Date()).format("X"),
                                	isCheckAdmin : false
                                })
        });
     // coming();
     document.getElementById('sendMess').value = '';
	console.log("countMess"+countMess);
	if(countMess==0)
	{
		firebase.firestore()
                        .collection("messages")
                        .add({
                            content: "TreeManager xin chào bạn...bạn cần chúng tôi hỗ trợ gì, vui lòng thông tin ở đây chúng tôi sẽ giải đáp Hotline:0385151444",
                            groupId: roomId,
                            isRead: false,
                            sender: "9MudqDJWkJZV3kfxUM1Uu9z1pym1",
                            timestamp: moment(new Date()).format("X")+10,
                            type: type
                        })
                        .then((ref) => {
                            console.log("Added doc with ID: ", ref);
                        });
		
	}
     return false;
}

var inputMessage = document.getElementById("sendMess");
inputMessage.addEventListener('keypress', (e) => {
	if(e.key == 'Enter') {
		sendMessage();
	}
});

function deleteMessage(self) {
     // get message ID
     var messageId = self.getAttribute("data-id");
     // delete message
     var deleteMess = document.getElementById("message-" + self.getAttribute("data-id"));
     deleteMess.innerHTML = "This message has been removed";
     db.collection('messages').doc(messageId).delete();
}