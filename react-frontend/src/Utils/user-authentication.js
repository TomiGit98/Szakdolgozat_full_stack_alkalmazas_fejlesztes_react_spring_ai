const TOKEN_KEY = 'postit_userid';

export const login = (id) => {
    localStorage.setItem(TOKEN_KEY, id);
}

export const logout = () => {
    localStorage.removeItem(TOKEN_KEY);
}

export const getId = () => {
    localStorage.getItem(TOKEN_KEY);
}

export const isLogin = () => {
    if(localStorage.getItem(TOKEN_KEY)){
        return true;
    }
    return false;
}