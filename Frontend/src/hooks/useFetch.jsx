import { useEffect, useState } from 'react';

import axios from 'axios';

const useFetch = (url, config = null) => {
  const [fetchData, setFetchData] = useState(null);
  const [isFetching, setIsFetching] = useState(true);
  const [fetchError, setFetchError] = useState(null);

  useEffect(() => {
    const abortController = new AbortController();

    axios.get(url, { ...config, signal: abortController.signal })
      .then(res => {
        if (res.status !== 200) throw Error(res.message);
        return res.data;
      })
      .then(data => {
        setFetchData(data);
        setFetchError(null);
        setIsFetching(false);
      })
      .catch(err => {
        if (err.name === 'AbortError') console.log('fetch aborted')
        else {
          if (err.response) setFetchError(err.response.data.message);
          else setFetchError(err.message);
          setIsFetching(false);
        }
      });

    return () => abortController.abort();
  }, [url]);

  return { fetchData, isFetching, fetchError };
}

export default useFetch;
