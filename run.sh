mvn assembly:assembly
if [ $? -ne 0 ]; then
    echo "Build did not pass"
    exit 1
fi

docker build -t esbase:latest .

docker tag esbase:latest kgsdora/esbase:latest

docker push kgsdora/esbase:latest
