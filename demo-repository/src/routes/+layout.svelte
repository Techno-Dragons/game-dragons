<script>
    import '../app.css';
    import Header from "./header.svelte";
    import Footer from "./footer.svelte";

    const {children} = $props();
</script>

<div class="h-screen">
    <Header/>
    {@render children()}
    <Footer/>
</div>