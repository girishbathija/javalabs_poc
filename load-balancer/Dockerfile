# Use the standard Nginx image from Docker Hub
FROM nginx

# The Dockerfile's author
LABEL Girish Bathija

# Copy the configuration file from the current directory and paste
# it inside the container to use it as Nginx's default config.
COPY nginx.conf /etc/nginx/nginx.conf

# This way we'll be able to
# access the server via localhost:80 on our host.
EXPOSE 80

# Start Nginx when the container has provisioned.
CMD ["nginx", "-g", "daemon off;"]