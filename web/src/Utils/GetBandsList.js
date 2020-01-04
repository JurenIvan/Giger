
export function getBandslist() {

      const response = await fetch('https://giger-backend-dev.herokuapp.com/api/bands/filter',null
      );
    
      const contentType = response.headers.get('content-type');
      const isJson = contentType && contentType.indexOf("application/json") !== -1;
    
      if (isJson) {
        const json = await response.json();
        if (response.ok) {
          return json;
        } else {
          throw json;
        }
      } else if (!response.ok) {
        throw response;
      } else {
        return response.text();
      }
}