import axiosInstance from '../configs/axios';

const authService = {
  googleLogin: (tokenId) => {
    return axiosInstance.post('/api/auth/google', { token: tokenId });
  },
};

export default authService;
