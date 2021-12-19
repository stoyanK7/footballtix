# Base image
FROM node:lts-alpine3.14
# Make 'app' folder the current working directory
WORKDIR /app
# Copy package.json and package-lock.json
COPY package*.json ./
# Install dependencies
RUN npm install
# Copy project files and folders to the current working directory
COPY . .
# Open the port on which the server will start
EXPOSE 3000
# Run the front end
CMD ["npm", "start"]