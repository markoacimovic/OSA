import AxiosClient from "./client/AxiosClient";

export const UsersService = {
    getUsers,
    blockUser
}

async function getUsers() {
    return await AxiosClient.get("administratori/all-users")
}

async function blockUser(blockedUser) {
    return await AxiosClient.post("administratori/block", blockedUser)
}