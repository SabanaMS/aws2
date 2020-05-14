// This file can be replaced during build by using the `fileReplacements` array.
// `ng build --prod` replaces `environment.ts` with `environment.prod.ts`.
// The list of file replacements can be found in `angular.json`.

export const environment = {
  production: false,
  
  // various APIs through API Gateway

  AUTH_API : 'http://apigateway:9000/user-service/api/v1/userservice',
    AUTH_LOGIN_API : 'http://apigateway:9000/user-service/api/v1/userservice/login',
  ADMIN_API_URL:'http://apigateway:9000/gym-service/api/v1/gymservice',
  USER_BOARD_API_URL:'http://apigateway:9000/gym-service/api/v1/gymservice',
  USER_QUERY_API_URL:'http://apigateway:9000/enquiry-service/api/v1/enquiryservice/admin/',
  USER_TICKET_API: 'http://apigateway:9000/ticket-service/api/v1/ticketservice',

};



