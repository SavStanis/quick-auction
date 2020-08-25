
const getToken = () => {
    return localStorage.getItem("token");
}
const buildAuthHeader = () => {
    return (localStorage.getItem("token")) ? "Bearer " + localStorage.getItem("token") : null;
}

export {getToken, buildAuthHeader};