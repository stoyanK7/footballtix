const calcReadTime = (text) => {
  if(!text) return 0;
  const averageTimePerWord = 600; // measured in ms
  const wordLength = text.split(' ').length;
  if (wordLength === 1) return 1500;
  return wordLength * averageTimePerWord;
};

export default calcReadTime;
