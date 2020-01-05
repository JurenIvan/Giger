import Cookies from "js-cookie"

export default function getBandslist(param) {

      const response =  fetch('https://giger-backend-dev.herokuapp.com/api/bands/filter',{
        method: "POST" ,
        body : null,
        headers: {
          "Content-Type" : "application/json",
          //"Authorization" : "Bearer " + Cookies.get("Bearer")
          "Authorization" : "Bearer " + "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJqb2huLmRvZUBnaWdlci5jb20iLCJleHAiOjE1NzgyNTU2NjUsImlhdCI6MTU3ODI1MjA2NX0.BuPLEsZ8j8vEOkMZeTzqgqNRVjaFGZv0TCmj5WZ2oRI"
      }})
      

      console.log(response)
      console.log(JSON.parse(response))
      
      return response;
}