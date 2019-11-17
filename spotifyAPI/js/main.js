function initiate() { //When you enter  the site
  if(sessionStorage.length <= 0) {
    Swal.fire({
      title: 'API Search Requires a Spotify Login!',
      text: "Click the 'Login' button to continue.",
      icon: 'info',
      showCancelButton: true,
      confirmButtonColor: '#1DB954',
      cancelButtonColor: '#d33',
      confirmButtonText: 'Login'
    }).then((result) => {
      if (result.value) {
        sessionStorage.setItem("one","two"); //Session storage is used, so the function doesnt run indefinetly
        grantToken();
      }
    })
  }
}
function success() { //Shows up after confirming the first alert
  if (sessionStorage.length >= 1) {
    Swal.fire({
      position: 'center',
      icon: 'success',
      title: 'You may now Search Artists, Albums and Songs !',
      showConfirmButton: false,
      timer: 2500,
      timerProgressBar: true,
    })
  }
}
function grantToken() {
  window.location.replace("https://accounts.spotify.com/authorize?client_id=c7e183fe68ed459e95e67fde31ce89b8&redirect_uri=https://nostalgic-edison-5f972e.netlify.com&scope=user-read-private%20user-read-email&response_type=token&state=123");
} //Grants access token so searches can be made

function search() {
  var url = document.URL,
  access_token = url.match(/\#(?:access_token)\=([\S\s]*?)\&/)[1]; //Pulls access token out of the url
  var q = document.getElementById('query').value; //The search query from form
  var type = document.getElementById('type').value; //The search type from form
  var auth = `Bearer ${access_token}`; //Access token for the AJAX request
  var searchUrl = `https://api.spotify.com/v1/search?q=${q}&type=${type}`; //Final search url for the AJAX request, using user inputs
  var xmlhttp = new XMLHttpRequest();
  xmlhttp.open("GET",searchUrl,true);
  xmlhttp.setRequestHeader("Content-Type", "application/json");
  xmlhttp.setRequestHeader("Authorization", auth);
  xmlhttp.send(); //Completed AJAX request with all the required information

  xmlhttp.onreadystatechange=function() {
    if (xmlhttp.readyState == 4 && xmlhttp.status == 200){
      console.log(xmlhttp.responseText);
       var data = JSON.parse(xmlhttp.responseText);
       var img = document.createElement('img'); //Img element for the picture div
       var text = document.createElement('p'); //P element for the 'link' div
       text.setAttribute('id','linkP') //Creating id for the elements, in order to style them.
       var info = document.createElement('p'); //P element for the 'info' div
       info.setAttribute('id','infoP')

       //Respone given by the AJAX Request is different based on the search type
       if (document.getElementById('type').value == 'artist') { //If user is searching for artists
         img.src = data.artists.items[0].images[0].url; // artist picture
         var link = document.createTextNode("Go To The Artist");
         text.onclick = function(e) {window.open(data.artists.items[0].external_urls.spotify,'_blank')}; //Onclick function for the previous text node that takes the user to the artist page
         text.append(link);
         text.style.cursor = "pointer"; //More intuitive for the user, if active element has a pointer on it.

         var artist = document.createTextNode("Artist: " + data.artists.items[0].name); // Text nodes For Artist name, followers and genre
         var followers = document.createTextNode("Followers: " + data.artists.items[0].followers.total);
         var genre = document.createTextNode("Genre: " + data.artists.items[0].genres[0]);
         info.append(artist);
         info.append(document.createElement('br'));
         info.append(genre);
         info.append(document.createElement('br'));
         info.append(followers);
       }
       if(document.getElementById('type').value == 'album') { //If user is searching for albums
         img.src = data.albums.items[0].images[0].url; //album picture
         var link = document.createTextNode("Go To The Album");
         text.onclick = function(e) {window.open(data.albums.items[0].external_urls.spotify,'_blank')};
         text.style.cursor = "pointer";
         text.append(link);
         var count = document.createTextNode("Song Count: " + data.albums.items[0].total_tracks); //Text Nodes for song count, release date, album name and artist name
         var release = document.createTextNode("Release Date: " + data.albums.items[0].release_date);
         var album = document.createTextNode("Album: " + data.albums.items[0].name);
         var artist = document.createTextNode("Artist: " + data.albums.items[0].artists[0].name);
         info.append(artist);
         info.append(document.createElement('br'));
         info.append(album);
         info.append(document.createElement('br'));
         info.append(release);
         info.append(document.createElement('br'));
         info.append(count);
       }
       if(document.getElementById('type').value == 'track') { //If user is searching for songs
        img.src = data.tracks.items[0].album.images[0].url; // Album pic via song
        var album = document.createTextNode("A Song Within: " + data.tracks.items[0].album.name); // Text nodes for album name, artist and release date
        var release = document.createTextNode("Album's Release Date: " + data.tracks.items[0].album.release_date);
        var artist = document.createTextNode("Artist: " + data.tracks.items[0].artists[0].name);
        info.append(artist);
        info.append(document.createElement('br'));
        info.append(album);
        info.append(document.createElement('br'));
        info.append(release);

        var link = document.createTextNode("Go To The Album");
        text.onclick = function(e) {window.open(data.tracks.items[0].external_urls.spotify,'_blank')};
        text.style.cursor = "pointer";
        text.append(link);
      }
      //$("#picture").html(img);
      document.getElementById('picture').innerHTML = '';
      document.getElementById('picture').append(img);

      //$("#info").html(info);
      document.getElementById('info').innerHTML = '';
      document.getElementById('info').append(info)

      //$("#link").html(text);
      document.getElementById('link').innerHTML = '';
      document.getElementById('link').append(text);

      //EMPTYING THE DIV WOULDNT BE NECESSARY IF jQuery usage was allowed, jQuery alternatives in comments above the code
   }
 }
}


//ALBUM SONG COUNT = document.getElementById('info').append(data.albums.items[0].total_tracks);
//ALBUM RELEASE DATE = document.getElementById('info').append(data.albums.items[0].release_date);
//ALBUM NAME = document.getElementById('info').append(data.albums.items[0].name);
//ARTIST NAME FROM ALBUM = document.getElementById('info').append(data.albums.items[0].artists[0].name);
