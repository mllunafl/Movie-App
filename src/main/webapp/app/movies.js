$(document).ready(function() {
   $(".dropdown-menu li a").click(function(event){
       var split = event.target.outerHTML.split(':');
       console.log('enum value =' + split[1])
       console.log('movie id =' + split[2])
       console.log('user id =' + split[3])

       if (split[3] == 'null') {
           alert("you must log in first")
           return
       }

          $.post( window.location.href + '/interest',
               { interest: split[1], movieId: split[2], username: split[3] });
   })
})
