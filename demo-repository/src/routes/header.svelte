<script>
  // import Cookies from "js-cookie";

  let loginCheck = $state({});

  async function logout() {
    await fetch(`/demo/member/logout`, {
      method: 'DELETE',
    });
    // Cookies.remove("accessToken");
  }

  async function logoutProcess() {
    await logout();
    toastNotice("로그아웃 되었습니다.");
    window.location.href = '/';
  }

</script>

<div class="navbar bg-base-100">
  <div class="navbar-start">
    <div class="dropdown">
      <div tabindex="0" role="button" class="btn btn-ghost btn-circle">
        <svg xmlns="http://www.w3.org/2000/svg" class="h-5 w-5" fill="none" viewBox="0 0 24 24" stroke="currentColor"><path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M4 6h16M4 12h16M4 18h7" /></svg>
      </div>
      <ul tabindex="0" class="menu menu-sm dropdown-content mt-3 z-[1] p-2 shadow bg-base-100 rounded-box w-52">
        <li><a href="/article/write"> <i class="fa-solid fa-rectangle-list"></i>글쓰기</a></li>
        <li><a class="nav-link" href="/member/signup"> <i class="fa-solid fa-user-plus"></i> 회원가입</a></li>
        <li><a class="nav-link" href="/member/login"> <i class="fa-solid fa-door-open"></i> 로그인</a></li>
        <li><a class="nav-link" href="/member/mypage"> <i class="fa-solid fa-address-card"></i> 마이페이지</a></li>
        <li><a class="nav-link" on:click={logoutProcess}> <i class="fa-solid fa-door-closed"></i>로그아웃</a></li>
      </ul>
    </div>
  </div>
  <div class="navbar-center">
    <a class="btn btn-ghost text-xl" href="/">Game Dragons</a>
  </div>
  <div class="navbar-end">
    <input type="text" placeholder="Type here" class="input input-bordered max-w-xs me-3" />
    <button class="btn btn-ghost btn-circle me-3">
      <svg xmlns="http://www.w3.org/2000/svg" class="h-5 w-5" fill="none" viewBox="0 0 24 24" stroke="currentColor"><path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M21 21l-6-6m2-5a7 7 0 11-14 0 7 7 0 0114 0z" /></svg>
    </button>
  </div>
</div>