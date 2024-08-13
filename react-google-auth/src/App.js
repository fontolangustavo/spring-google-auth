import React from 'react';
import { GoogleOAuthProvider, GoogleLogin } from '@react-oauth/google';

import authService from './services/authService';

function App() {
  const handleLoginSuccess = async (credentialResponse) => {
    try {
      const tokenId = credentialResponse.credential;

      const response = await authService.googleLogin(tokenId);

      console.log('Login successful', response);
    } catch (error) {
      console.error('Login Failed', error);
    }
  };

  const handleError = () => {
    console.log('Login Failed');
  };


  return (
    <GoogleOAuthProvider clientId={process.env.REACT_APP_GOOGLE_CLIENT_ID}>
      <GoogleLogin
        onSuccess={handleLoginSuccess}
        onError={handleError}
      />
    </GoogleOAuthProvider>
  );
}

export default App;
