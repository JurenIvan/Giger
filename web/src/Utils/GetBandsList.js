import Cookies from "js-cookie"

export default  function getBandslist(param) {




      const response =  fetch('https://giger-backend-dev.herokuapp.com/api/bands/filter',{
        method: "POST" ,
        body : null,
        headers: {
          "Content-Type" : "application/json",
          //"Authorization" : "Bearer " + Cookies.get("Bearer")
          "Authorization" : "Bearer " + "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJqb2huLmRvZUBnaWdlci5jb20iLCJleHAiOjE1NzgyNDYyNzAsImlhdCI6MTU3ODI0MjY3MH0.LWpjefFvpWt8EPAfqAlskIAnN2nrc84Eyz6AGVx4W9I"
      }
      )

      return JSON.parse(response);
}