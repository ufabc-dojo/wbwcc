fn main() {
    let hl = [0o157, 0o151, 0o154, 0o251, 0o303, 0o110];
    let wd = [0o144, 0o154, 0o162, 0o157, 0o127];

    let mut dwlh: Vec<u8> = Vec::new();

    for w in wd.iter() {
        dwlh.push(*w);
    }
    dwlh.push(b' ');
    for h in hl.iter() {
        dwlh.push(*h);
    }

    let hlwd: Vec<u8> = dwlh
        .iter()
        .rev()
        .map(|x| x.to_owned())
        .collect();

    let ans = match String::from_utf8(hlwd) {
        Ok(s) => s,
        Err(_) => String::new(),
    };
    
    println!("{}", ans);
}
