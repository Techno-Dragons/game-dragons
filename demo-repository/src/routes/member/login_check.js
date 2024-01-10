import {goto} from "$app/navigation";
import {isLogin} from "$lib/login_stores.js";
import {onMount} from "svelte";

/**
 * @param {string} msg
 */
export function checkLogout(msg) {
    onMount(() => {
        if (localStorage.getItem("nickname") != undefined) {
            alert(msg);
            goto("/");
        }
    })
}

/**
 * @param {string} msg
 */
export function checkLogin(msg) {
    onMount(() => {
        if (localStorage.getItem("nickname")) {
            alert(msg);
            goto("/");
        }
    })
}

export async function logout() {
    await fetch(`https://gamedragons.api.bi3a.app/member/logout`, {
        headers: {
            'Content-Type': 'application/json'
        },
        method: 'POST',
        credentials: 'include'
    });
    localStorage.removeItem("username");
    localStorage.removeItem("nickname");
    isLogin.set(false);
    await goto('/');
}