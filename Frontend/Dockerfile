FROM node:lts-alpine3.14

WORKDIR /app

COPY package*.json ./

RUN npm install

RUN npm install -g serve

COPY . .

ARG REACT_APP_API_ENDPOINT

RUN sh create-env-file.sh REACT_APP_API_ENDPOINT=$REACT_APP_API_ENDPOINT

RUN npm run build

EXPOSE 3000

CMD ["serve", "-s", "build"]