import Cookies from "js-cookie"

export default function getBandslist(param) {

      const response =  fetch('https://giger-backend-dev.herokuapp.com/api/bands/filter',{
        method: "POST" ,
        body : null,
        headers: {
          "Content-Type" : "application/json",
          //"Authorization" : "Bearer " + Cookies.get("Bearer")
          "Authorization" : "Bearer " + Cookies.get("Bearer")
      }})
      

      console.log(response)
      console.log(JSON.parse(response))
      
      return response;
}