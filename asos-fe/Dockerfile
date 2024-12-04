# Use Node.js 18 as a base image
FROM node:18-alpine

# Set the working directory inside the container
WORKDIR /app

# Copy only package.json and package-lock.json initially
COPY package*.json ./

# Install dependencies
RUN npm install

# Copy the entire application to the container
COPY . .

# Set environment variables for hot-reloading
ENV CHOKIDAR_USEPOLLING=true
ENV WDS_SOCKET_PORT=0

# Expose the port the app runs on
EXPOSE 3000

# Start the application
CMD ["npm", "start"]
