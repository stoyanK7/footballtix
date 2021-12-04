import pad from './pad';

const formatDate = (date) => {
  if (!(date instanceof Date)) date = new Date(date);
  const weekDay = date.toLocaleDateString('en-US', { weekday: 'long' });
  const day = date.getDate();
  const month = date.toLocaleString('en-US', { month: 'long' });
  const year = date.getFullYear();
  const hours = pad(date.getHours(), 2)
  const minutes = pad(date.getMinutes(), 2);
  return `${weekDay}, ${day} ${month} ${year} ${hours}:${minutes}`;
};

export default formatDate;
