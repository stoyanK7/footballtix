import { useState } from "react";

export default function useUserId() {
  const getUserId = () => {
    const userId = localStorage.getItem('userId');
    return userId || undefined;
  };

  const [userId, setUserId] = useState(getUserId());

  return {
    setUserId,
    userId
  };
};
