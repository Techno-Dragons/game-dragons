<script>
  import {onMount} from "svelte";
  import {logout} from "./member/login_check.js";
  import {toastNotice} from "../app.js";

  let isLogin = false;

  async function logoutProcess() {
    await logout();
    toastNotice("로그아웃 되었습니다.");

    isLogin = false;
  }

  onMount(() => {
    isLogin = !!localStorage.getItem('nickname');
  });
</script>

<div class="navbar bg-base-100">
  <div class="navbar-start">
    <div class="dropdown">
      <div tabindex="0" role="button" class="btn btn-ghost btn-circle">
        <i class="fa-solid fa-bars"></i>
      </div>
      <ul tabindex="0" class="menu menu-sm dropdown-content mt-3 z-[1] p-2 shadow bg-base-100 rounded-box w-52">
        <li><a href="/article"> <i class="fa-solid fa-rectangle-list"></i>글 목록</a></li>
        {#if isLogin}
          <li><a class="nav-link" href="/member/mypage"> <i class="fa-solid fa-address-card"></i> 마이페이지</a></li>
          <li><a class="nav-link" on:click={logoutProcess}> <i class="fa-solid fa-door-closed"></i>로그아웃</a></li>
        {:else}
          <li><a class="nav-link" href="/member/signup"> <i class="fa-solid fa-user-plus"></i> 회원가입</a></li>
          <li><a class="nav-link" href="/member/login"> <i class="fa-solid fa-door-open"></i> 로그인</a></li>
        {/if}
      </ul>
    </div>
  </div>
  <div class="navbar-center">
    <a class="btn btn-ghost text-xl" href="/">Game Dragons</a>
  </div>
  <div class="navbar-end">
    <input type="text" placeholder="Type here" class="input input-bordered max-w-xs me-3" />
    <button class="btn btn-ghost btn-circle me-3">
      <i class="fa-solid fa-magnifying-glass"></i>
    </button>
  </div>
</div>