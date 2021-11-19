import { useEffect, useState } from 'react';

import axios from 'axios';

const useGET = (url, config = null) => {
  const [data, setData] = useState(null);
  const [isPending, setIsPending] = useState(true);
  const [error, setError] = useState(null);

  useEffect(() => {
    const abortController = new AbortController();

      axios.get(url, { ...config, signal: abortController.signal })
        .then(res => {
          if (res.status !== 200) throw Error(res.message);
          return res.data;
        })
        .then(data => {
          setData(data);
          setError(null);
          setIsPending(false);
        })
        .catch(err => {
          if (err.name === 'AbortError') console.log('fetch aborted')
          else {
            if(err.response) setError(err.response.data.message);
            else setError(err.message);
            setIsPending(false);
          }
        });

    return () => abortController.abort();
  }, [url]);

  return { data, isPending, error };
}

export default useGET;
