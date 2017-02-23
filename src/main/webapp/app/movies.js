$(document).ready(function() {
   $(".interesting").change(function(event){
       var split = event.target.value.split(':');
       console.log('enum value =' + split[1])
       console.log('movie id =' + split[2])
       console.log('user id =' + split[3])

       if (split[3] == 'null') {
           alert("you must log in first")
           return
       }
          var nohash = window.location.href.split('#')[0];
                $.post( nohash + '/interest',
               { interest: split[1], movieId: split[2], username: split[3] });
   })
})
