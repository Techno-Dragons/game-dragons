<script>

	let username="";
	let password="";

	function checkCookie(cookieName) {
		// 현재 페이지의 모든 쿠키를 가져옴
		var cookies = document.cookie;

		// 쿠키 이름이 존재하는지 확인
		var cookieExists = cookies.includes(cookieName);

		return cookieExists;
	}
	var isTokenExists = checkCookie("accessToken");

	if (isTokenExists) {
		console.log("쿠키가 존재합니다.");
	} else {
		console.log("쿠키가 존재하지 않습니다.");
	}

	async function login() {
		await fetch(`http://localhost:8090/member/login`, {
			method: 'POST',
			body : JSON.stringify({
				username : this.state.username,
				password : this.state.password
			})
		}).then((response)=>{

		});
	}
</script>

<svelte:head>
	<title>Home</title>
	<meta name="description" content="Svelte demo app" />
</svelte:head>

<section>
	<h1>유저 로그인</h1>
	<form>
		<div>
			<label for="username">사용자ID</label>
			<input class="textarea textarea-bordered" placeholder="username" type="text" id="username" bind:value={username}>
		</div>
		<div>
			<label for="password">비밀번호</label>
			<input class="textarea textarea-bordered" placeholder="password" type="password" id="password" bind:value={password}>
		</div>
		<button type="submit" on:click={login()} >로그인</button>
	</form>

</section>

<style>

</style>
